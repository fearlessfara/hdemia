package org.hdemia.hdemia.be.controller.dto;

public record RegistrationRequestDTO(String name, String surname, String middleName, Integer regNumber, String email, String password) {
}
