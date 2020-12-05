package co.edu.javeriana.services.notifications.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

@Data
@ApiModel(description = "Estructura de entrada que se asocia al cuerpo, y datos necesarios para realizar una notificación.")
public class Notification implements java.io.Serializable {
    @ApiModelProperty(notes = "Especifica a quien se le debe enviar la notificación")
    private String to;
    @ApiModelProperty(notes = "Especifica el asutno al que se asocia la notificación.")
    private String subject;
    @ApiModelProperty(notes = "Cuerpo de la notificacion")
    private String body;
    @ApiModelProperty(notes = "Estructura en la cual se desea ver la notificación, a nivel interno es una especificación HTML.")
    private String template;
    @ApiModelProperty(notes = "Parametros de soporte que se requieren para agregar valor a la notificación..")
    private Map<String, Object> params;

}
