package com.example.bibliotecaApp.entity;

import java.util.List;

public class ResponseLendings {

    private Long totalElement;
    private List<LendingDTO> response;

    public ResponseLendings(Long totalElement) {
        this.totalElement = totalElement;
    }

    public ResponseLendings(Long totalElement, List<LendingDTO> response) {
        this.totalElement = totalElement;
        this.response = response;
    }

    public Long getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(Long totalElement) {
        this.totalElement = totalElement;
    }

    public List<LendingDTO> getResponse() {
        return response;
    }

    public void setResponse(List<LendingDTO> response) {
        this.response = response;
    }
}
