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
}
