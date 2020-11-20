package co.edu.javeriana.services.notifications.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "Estructura de respuesta que se le remite al consumidor del funcionamiento de envíar notificaciones.")
public class Response implements java.io.Serializable {
    @ApiModelProperty(notes = "Código al cual se asocia una respuesta del tipo HTTP")
    private String code;
    @ApiModelProperty(notes = "Descripción de la posible respuesta que se puede emitir, este valor es basado en el comportamiento interno.")
    private String description;

}
