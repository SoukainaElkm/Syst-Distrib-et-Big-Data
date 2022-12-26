package elkamouni.soukaina.examenFinalAPI.events;

import lombok.Getter;

public class CustomerCreatedEvent extends BaseEvent<Long>{
    @Getter private String name;
    @Getter private String email;
    @Getter private String tel;
    public CustomerCreatedEvent(Long id, String name, String email, String tel) {
        super(id);
        this.email=email;
        this.name=name;
        this.tel=tel;
    }
}
