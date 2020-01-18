package com.slusarz.pokerafterdark.spring.token;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode(exclude = "expirationDate")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Token {

    private String value;

    private LocalDateTime expirationDate;

    public void prolongate(int timeInMinutes) {
        expirationDate = LocalDateTime.now().plusMinutes(timeInMinutes);
    }

    public boolean isExpired() {
        return expirationDate.isBefore(LocalDateTime.now());
    }

}
