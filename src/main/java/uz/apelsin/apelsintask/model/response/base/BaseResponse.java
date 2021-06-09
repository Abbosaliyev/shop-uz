package uz.apelsin.apelsintask.model.response.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 Created by
 Sahobiddin Abbosaliyev
 6/7/2021
*/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseResponse {
    private boolean success;
    private String message;
    private Object obj;
}
