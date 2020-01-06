package com.baronzhang.android.weather.base


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @author baronzhang (微信公众号：BaronTalk)
 */
open class BaseFragment : Fragment() {

    //    private PublishSubject<FragmentLifecycleEvent> fragmentLifecycleSubject = PublishSubject.create();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroyView() {
        //        fragmentLifecycleSubject.onNext(FragmentLifecycleEvent.DESTROY_VIEW);
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
