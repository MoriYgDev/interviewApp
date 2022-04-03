package ir.moris.interviewapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ir.moris.interviewapp.R
import ir.moris.interviewapp.databinding.ItemPersonBinding
import ir.moris.interviewapp.db.Person

class DbRVAdapter() : RecyclerView.Adapter<DbRVAdapter.DbViewHolder>() {

    private lateinit var binding: ItemPersonBinding

    inner class DbViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DbViewHolder {
        binding = ItemPersonBinding.bind(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_person, parent, false)
        )
        return DbViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: DbViewHolder, position: Int) {
        binding.tvPersonInfo.text = differ.currentList[position].toString()
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val differCallback = object : DiffUtil.ItemCallback<Person>(){
        override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this , differCallback)
}