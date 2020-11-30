package io.adev.itschool.robot.platform.arena

import android.animation.Animator
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.doOnLayout
import androidx.core.view.updateLayoutParams
import io.adev.itschool.robot.R
import io.adev.itschool.robot.common.arena.ArenaView
import io.adev.itschool.robot.common.arena.ArenaViewModel
import io.adev.itschool.robot.common.arena.UserAction
import io.adev.itschool.robot.common.arena.entity.*
import io.adev.itschool.robot.databinding.ArenaFragmentBinding
import io.adev.itschool.robot.platform.BaseFragment

class ArenaFragment : BaseFragment(), ArenaView {
    private val binding by viewBinding { ArenaFragmentBinding.inflate(it) }

    override val viewModel by bindViewModel {
        val arenaDraw = requireArguments().getString(arenaDrawKey)!!
        val arena = arenaDraw.parseArena()
        ArenaViewModel(
            arena = arena,
            userAction = requireNotNull(userAction) { "userAction must not be null" },
            executor = AndroidRobotExecutor(),
            statesApplier = AndroidRobotStatesApplier()
        )
    }

    private var pointSize: Float? = null
    override var arena: Arena? by didSetNotNull { arena ->
        requireView().doOnLayout { view ->
            pointSize = arena.calculatePointSize(
                Size.Real(
                    width = view.width.rp,
                    height = view.height.rp
                )
            ).also { pointSize ->
                drawArena(arena, pointSize)
            }
            updateRobot()
        }
    }

    override var robotState: RobotState? by didSet {
        updateRobot()
    }

    override val displayWon: () -> Unit = {
        AlertDialog.Builder(requireContext())
            .setTitle("You won!")
            .setPositiveButton("OK") { _, _ ->
                requireActivity().finish()
            }
            .show()
    }

    private var currentDrawnRobotState: RobotState? = null
    private fun updateRobot() {
        val robot = robotState ?: return
        val pointSize = pointSize ?: return
        val robotView = robotView(robot, pointSize)
        moveRobot(robotView, robot, pointSize)
        currentDrawnRobotState = robot
    }

    private fun drawArena(arena: Arena, pointSize: Float) {
        val width = arena.size.width.render(pointSize)
        val height = arena.size.height.render(pointSize)
        binding.arenaContainer.updateLayoutParams {
            this.width = width
            this.height = height
        }
        binding.robotContainer.updateLayoutParams {
            this.width = width
            this.height = height
        }
        binding.arenaContainer.removeAllViews()
        arena.blocks.forEach { block ->
            val blockView = createBlockView(block, pointSize)
            binding.arenaContainer.addView(blockView)
        }
    }

    private fun createBlockView(block: Block, pointSize: Float): View {
        val container = FrameLayout(requireContext())
        container.layoutParams = ViewGroup.MarginLayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).also {
            it.leftMargin = block.position.x.render(pointSize)
            it.topMargin = block.position.y.render(pointSize)
        }
        val blockView = ImageView(requireContext())
        blockView.layoutParams = FrameLayout.LayoutParams(
            block.size.width.render(pointSize),
            block.size.height.render(pointSize)
        )
        blockView.scaleType = ImageView.ScaleType.FIT_XY
        when (block.texture) {
            Block.Texture.Platform -> {
                container.foreground = ContextCompat.getDrawable(requireContext(), R.drawable.block_corner)
                blockView.setImageResource(R.drawable.stone_texture)
            }
            Block.Texture.Target -> {
                blockView.setImageResource(R.drawable.target)
            }
            null -> {
                blockView.setImageDrawable(null)
            }
        }
        container.addView(blockView)
        return container
    }

    private var isRobotInit = false
    private var robotView: View? = null
    private fun robotView(robotState: RobotState, pointSize: Float): View {
        return robotView ?: ImageView(requireContext()).also {
            it.layoutParams = FrameLayout.LayoutParams(
                robotState.size.width.render(pointSize),
                robotState.size.height.render(pointSize)
            )
            it.scaleType = ImageView.ScaleType.FIT_XY
            it.setImageResource(R.drawable.robot)
            binding.robotContainer.removeAllViews()
            binding.robotContainer.addView(it)
            robotView = it
            isRobotInit = false
        }
    }

    private fun moveRobot(robotView: View, robotState: RobotState, pointSize: Float) {
        val newX = robotState.position.x.render(pointSize)
        val newY = robotState.position.y.render(pointSize)
        if (isRobotInit) {
            robotView.animate()
                .setDuration(500L)
                .translationX(newX.toFloat())
                .translationY(newY.toFloat())
                .setListener(object : Animator.AnimatorListener {

                    override fun onAnimationEnd(animation: Animator?) {
                        viewModel.onRobotMoved()
                    }

                    override fun onAnimationRepeat(animation: Animator?) {}
                    override fun onAnimationCancel(animation: Animator?) {}
                    override fun onAnimationStart(animation: Animator?) {}
                })
                .start()
        } else {
            robotView.translationX = newX.toFloat()
            robotView.translationY = newY.toFloat()
            isRobotInit = true
            viewModel.onRobotMoved()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        robotView = null
    }

    companion object {

        fun newInstance(arenaDraw: String, userAction: UserAction): ArenaFragment {
            this.userAction = userAction
            return ArenaFragment().also {
                it.arguments = bundleOf(
                    arenaDrawKey to arenaDraw
                )
            }
        }

        private var userAction: UserAction? = null

        private const val arenaDrawKey = "ARENA_DRAW"
    }
}