package interfaces

import androidx.lifecycle.MutableLiveData
import com.zoltanlorinczi.project_retrofit.api.model.TaskResponse

interface TaskItemClickListener {
    fun onCardClicked(task: TaskResponse)
}