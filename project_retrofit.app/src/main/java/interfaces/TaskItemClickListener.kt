package interfaces

import com.zoltanlorinczi.project_retrofit.api.model.TaskResponse

interface TaskItemClickListener {
    fun onCardClicked(task: TaskResponse)
}