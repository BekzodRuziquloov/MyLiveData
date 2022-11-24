package space.beka.project.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import space.beka.project.databinding.ItemView1Binding
import space.beka.project.databinding.ItemView2Binding
import space.beka.project.utils.MyData

class RvAdapter(val lifecycleOwner:LifecycleOwner) : RecyclerView.Adapter<ViewHolder>() {
    inner  class Vh1(var itemRvBinding: ItemView1Binding):RecyclerView.ViewHolder(itemRvBinding.root) {
        fun onBind() {
itemRvBinding.edtName.addTextChangedListener {
    MyData.myLiveData.postValue(it.toString())
}
        }
    }
    inner  class Vh2(var itemRvBinding: ItemView2Binding):RecyclerView.ViewHolder(itemRvBinding.root) {
        fun onBind() {
MyData.myLiveData.observe(lifecycleOwner){
    itemRvBinding.edtName.text = it
}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder  {
        return if (viewType==0){

            Vh1(ItemView1Binding.inflate(LayoutInflater.from(parent.context), parent, false))
        }else{

            Vh2(ItemView2Binding.inflate(LayoutInflater.from(parent.context), parent, false))
        }


    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position==0){
            (holder as Vh1).onBind()
        }else{

            (holder as Vh2).onBind()
        }
    }

    override fun getItemCount(): Int =2


    override fun getItemViewType(position: Int): Int {
        return position
    }
}