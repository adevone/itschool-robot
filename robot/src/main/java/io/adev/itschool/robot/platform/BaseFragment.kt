package io.adev.itschool.robot.platform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import io.adev.itschool.robot.common.BaseViewModelController
import summer.android.SummerFragment
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

abstract class BaseFragment : SummerFragment() {

    abstract val viewModel: BaseViewModelController

    private var createBinding: ((LayoutInflater) -> ViewBinding)? = null
    protected fun <TBinding : ViewBinding> viewBinding(
        createBinding: (LayoutInflater) -> TBinding
    ): ViewBindingDelegate<TBinding> {
        this.createBinding = createBinding
        return ViewBindingDelegate()
    }

    private var _binding: ViewBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = (createBinding ?: throw ViewBindingNotProvidedException())(inflater)
        return _binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

    protected inner class ViewBindingDelegate<TBinding : ViewBinding>
        : ReadOnlyProperty<Any?, TBinding> {

        override fun getValue(thisRef: Any?, property: KProperty<*>): TBinding {
            @Suppress("UNCHECKED_CAST")
            return _binding!! as TBinding
        }
    }
}

class ViewBindingNotProvidedException : IllegalStateException()