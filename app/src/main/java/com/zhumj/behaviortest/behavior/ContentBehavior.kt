package com.zhumj.behaviortest.behavior

import android.content.Context
import android.graphics.Rect
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View
import com.zhumj.behaviortest.R

/**
 * @author Created by Administrator
 * @date on 2018/4/12
 * @function
 */
class ContentBehavior<V : View>(context: Context?, attrs: AttributeSet?) : CoordinatorLayout.Behavior<V>(context, attrs) {

    private val rect = Rect()

    override fun onLayoutChild(parent: CoordinatorLayout?, child: V, layoutDirection: Int): Boolean {
        parent?.onLayoutChild(child, layoutDirection)
        //获取 child 依赖的 View
        val headView = findDependencyView(parent?.getDependencies(child) ?: ArrayList())
        //child 的 Top 向下移动 headView?.height，使 child 在 headView 下面
        child.layout(child.left, headView?.height ?: 0, child.right, child.bottom)
        //记录View原始的四围
        rect.set(child.left, child.top, child.right, child.bottom)
        return true
    }

    private fun findDependencyView(views: List<View>): View? {
        return views.firstOrNull { it.id == R.id.behavior_header }
    }

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: V, dependency: View?): Boolean {
        return dependency?.id == R.id.behavior_header
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout?, child: V, dependency: View?): Boolean {
        val top = rect.top + (dependency?.translationY ?: 0f).toInt()
        //随着 DependentView 的移动，改变  child 的 Top 值
        child.layout(rect.left, if (top <= 0) 0 else top, rect.right, rect.bottom)
        return true
    }
}