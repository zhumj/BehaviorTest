package com.zhumj.behaviortest.behavior

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View
import android.support.v4.view.ViewCompat

/**
 * @author Created by Administrator
 * @date on 2018/4/12
 * @function Head
 */
class HeadBehavior<V : View>(context: Context?, attrs: AttributeSet?) : CoordinatorLayout.Behavior<V>(context, attrs) {

    private var offset = 0

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: V, directTargetChild: View, target: View, axes: Int, type: Int): Boolean {
        //axes == ViewCompat.SCROLL_AXIS_VERTICAL 判断是否是上下滑动
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: V, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type)
        offset += dyConsumed
        if (dyConsumed > 0) {//手指向上滑动，对应向下滚动
            if (child.height > offset) {
                child.translationY = -offset.toFloat()
            } else {
                child.translationY = -child.height.toFloat()
            }
        } else {//手指向下滑动， 对应向上滚动
            if (offset > 0) {
                child.translationY = -offset.toFloat()
            } else {
                child.translationY = 0f
            }
        }
    }
}