package io.adev.itschool.robot.platform.arena

import android.animation.Animator
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.doOnLayout
import androidx.core.view.updateLayoutParams
import io.adev.itschool.robot.R
import io.adev.itschool.robot.common.arena.ArenaView
import io.adev.itschool.robot.common.arena.ArenaViewModel
import io.adev.itschool.robot.common.arena.UserAction
import io.adev.itschool.robot.common.arena.entity.RobotState
import io.adev.itschool.robot.common.arena.entity.Size
import io.adev.itschool.robot.common.arena.entity.arena.Arena
import io.adev.itschool.robot.common.arena.entity.arena.blocks.Block
import io.adev.itschool.robot.common.arena.entity.arena.blocks.PasswordBlock
import io.adev.itschool.robot.common.arena.entity.arena.blocks.PlatformBlock
import io.adev.itschool.robot.common.arena.entity.arena.blocks.TargetBlock
import io.adev.itschool.robot.common.arena.entity.rp
import io.adev.itschool.robot.databinding.ArenaFragmentBinding
import io.adev.itschool.robot.databinding.RobotViewBinding
import io.adev.itschool.robot.platform.BaseFragment

class ArenaFragment : BaseFragment(), ArenaView {
    private val binding by viewBinding { ArenaFragmentBinding.inflate(it) }

    override val viewModel by bindViewModel {
        ArenaViewModel(
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
        val robotState = robotState
        val pointSize = pointSize
        if (robotState != null && pointSize != null) {
            val robotBinding = robotBinding(robotState, pointSize)
            setRobotText(robotBinding, robotState.text)
            moveRobot(robotBinding, robotState, pointSize)
            robotBinding.root.alpha = if (!robotState.isDestroyed) 1f else 0.3f
            currentDrawnRobotState = robotState
        }
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
        container.addView(blockView)
        when (block) {
            is PlatformBlock -> {
                container.foreground = ContextCompat.getDrawable(requireContext(), R.drawable.block_corner)
                blockView.setImageResource(R.drawable.stone_texture)
            }
            is TargetBlock -> {
                blockView.setImageResource(R.drawable.target)
            }
            is PasswordBlock -> {
                val passwordView = TextView(requireContext())
                passwordView.textSize = 32f
                passwordView.setTextColor(Color.parseColor("#D50000"))
                passwordView.typeface = Typeface.DEFAULT_BOLD
                passwordView.layoutParams = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    gravity = Gravity.CENTER
                }
                passwordView.text = block.password
                container.addView(passwordView)
                blockView.setImageResource(R.drawable.password_texture)
            }
            else -> {
                blockView.setImageDrawable(null)
            }
        }
        return container
    }

    private var isRobotInit = false
    private var robotBinding: RobotViewBinding? = null
    private fun robotBinding(robotState: RobotState, pointSize: Float): RobotViewBinding {
        if (robotBinding != null) return robotBinding!!
        val robotBinding = RobotViewBinding.inflate(layoutInflater)
        robotBinding.root.layoutParams = FrameLayout.LayoutParams(
            robotState.size.width.render(pointSize),
            robotState.size.height.render(pointSize)
        )
        binding.robotContainer.removeAllViews()
        binding.robotContainer.addView(robotBinding.root)
        this.robotBinding = robotBinding
        isRobotInit = false
        return robotBinding
    }

    private fun setRobotText(robotBinding: RobotViewBinding, text: String) {
        robotBinding.textView.text = text
    }

    private fun moveRobot(robotBinding: RobotViewBinding, robotState: RobotState, pointSize: Float) {
        val newX = robotState.position.x.render(pointSize)
        val newY = robotState.position.y.render(pointSize)
        if (isRobotInit) {
            robotBinding.root.animate()
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
            robotBinding.root.translationX = newX.toFloat()
            robotBinding.root.translationY = newY.toFloat()
            isRobotInit = true
            viewModel.onRobotMoved()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        robotBinding = null
    }

    companion object {

        fun newInstance(userAction: UserAction): ArenaFragment {
            this.userAction = userAction
            return ArenaFragment()
        }

        private var userAction: UserAction? = null
    }
}