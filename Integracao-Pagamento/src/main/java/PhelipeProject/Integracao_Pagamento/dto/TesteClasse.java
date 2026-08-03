package PhelipeProject.Integracao_Pagamento.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class TesteClasse {
    private int id;

    private String type;

    private String  action;

    private TesteClasseData data;
}
