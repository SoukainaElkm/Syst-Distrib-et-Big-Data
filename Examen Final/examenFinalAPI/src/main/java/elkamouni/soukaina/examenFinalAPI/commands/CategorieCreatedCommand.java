package elkamouni.soukaina.examenFinalAPI.commands;

import lombok.Getter;

public class CategorieCreatedCommand extends BaseCommand<Long>{
    @Getter
    private String name;
    @Getter private String Description;
    public CategorieCreatedCommand(Long id, String name, String Description) {
        super(id);
        this.Description=Description;
        this.name=name;
    }
}
