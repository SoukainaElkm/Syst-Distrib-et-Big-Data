package elkamouni.soukaina.examenFinalAPI.events;

import lombok.Getter;

public class CategorieCreatedEvent extends BaseEvent<Long>{
    @Getter private String name;
    @Getter private String Description;
    public CategorieCreatedEvent(Long id, String name, String Description) {
        super(id);
        this.Description=Description;
        this.name=name;
    }
}
