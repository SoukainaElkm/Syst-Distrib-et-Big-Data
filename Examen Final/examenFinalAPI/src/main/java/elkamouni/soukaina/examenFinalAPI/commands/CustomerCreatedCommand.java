package elkamouni.soukaina.examenFinalAPI.commands;

import lombok.Getter;

public class CustomerCreatedCommand extends BaseCommand<Long>{
    @Getter
    private String name;
    @Getter private String email;
    @Getter private String tel;
    public CustomerCreatedCommand(Long id, String name, String email, String tel) {
        super(id);
        this.email=email;
        this.name=name;
        this.tel=tel;
    }
}
