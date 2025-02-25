package co.istad.backend.base;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BasedError<T> {

    private String code;

    private T description;


}
