package uz.pdp.entity.task;

import lombok.*;
import uz.pdp.entity.BaseEntity;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task extends BaseEntity {
    private String name;
    private String description;
    private UUID assigneeId;
    private TaskType type;
    private TaskStatus status;
    private int order;
    private UUID creatorId;

    public Task(String name, String description,TaskType type, TaskStatus status,UUID creatorId) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.status = status;
        this.creatorId = creatorId;
    }

    @Override
    public String toString() {
        return "Task{" +
                "order : '" + order + '\'' +
                ", name : '" + name + '\'' +
                ", description : '" + description + '\'' +
                ", assigneeId : '" + assigneeId +
                ", creatorId : '" + creatorId +
                ", type : '" + type +
                ", status : '" + status +
                '}';
    }
}
