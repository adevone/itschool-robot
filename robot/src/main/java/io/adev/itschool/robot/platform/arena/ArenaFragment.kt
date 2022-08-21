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
import io.adev.itschool.robot.common.arena.CreateRobotController
import io.adev.itschool.robot.common.arena.entity.RobotState
import io.adev.itschool.robot.common.arena.entity.Size
import io.adev.itschool.robot.common.arena.entity.arena.Arena
import io.adev.itschool.robot.common.arena.entity.arena.Asset
import io.adev.itschool.robot.common.arena.entity.arena.Block
import io.adev.itschool.robot.common.arena.entity.rp
import io.adev.itschool.robot.databinding.ArenaFragmentBinding
import io.adev.itschool.robot.databinding.RobotViewBinding
import io.adev.itschool.robot.exhaustive
import io.adev.itschool.robot.platform.BaseFragment

class ArenaFragment : BaseFragment(), ArenaView {
    private val binding by viewBinding { ArenaFragmentBinding.inflate(it) }

    override val viewModel by bindViewModel {
        ArenaViewModel(
            createRobotController = requireNotNull(createRobotController) {
                "userAction must not be null"
            },
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
            robotBinding.root.alpha = if (robotState.finishReason == null) 1f else 0.3f
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
        val heightInPx = block.size.height.render(pointSize)
        blockView.layoutParams = FrameLayout.LayoutParams(
            block.size.width.render(pointSize),
            heightInPx
        )
        blockView.scaleType = ImageView.ScaleType.FIT_XY
        container.addView(blockView)
        when (val asset = block.asset) {
            is Asset.Void -> {
                blockView.setImageDrawable(null)
            }
            Asset.Platform -> {
                container.foreground = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.block_corner
                )
                blockView.setImageResource(R.drawable.stone_texture)
            }
            Asset.Target -> {
                blockView.setImageResource(R.drawable.target)
            }
            Asset.CheckKey -> {
                blockView.setImageResource(R.drawable.password_texture)
            }
            is Asset.Password -> {
                blockView.setImageResource(R.drawable.password_texture)
                val passwordView = blockTextView(heightInPx, 0.4f)
                passwordView.text = asset.password
                container.addView(passwordView)
            }
            is Asset.Code -> {
                val codeView = blockTextView(heightInPx, 0.7f)
                codeView.text = asset.randomCode.toString()
                container.addView(codeView)
            }
            is Asset.CheckCode -> {
                blockView.setImageResource(R.drawable.verify)
            }
        }.exhaustive
        return container
    }

    private fun blockTextView(blockHeightInPx: Int, sizeCof: Float): TextView {
        val textView = TextView(requireContext())
        val heightInDp = blockHeightInPx / resources.displayMetrics.density
        textView.textSize = heightInDp * sizeCof
        textView.setTextColor(Color.parseColor("#D50000"))
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        ).apply {
            gravity = Gravity.CENTER
        }
        return textView
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

    private fun moveRobot(
        robotBinding: RobotViewBinding,
        robotState: RobotState,
        pointSize: Float
    ) {
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

        fun newInstance(createRobotController: CreateRobotController): ArenaFragment {
            this.createRobotController = createRobotController
            return ArenaFragment()
        }

        private var createRobotController: CreateRobotController? = null
    }
}