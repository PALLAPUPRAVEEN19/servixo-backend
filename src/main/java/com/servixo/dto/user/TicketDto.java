package com.servixo.dto.user;

public class TicketDto {

    private Long id;
    private String issue;
    private String status;
    private Long userId;

    public TicketDto() {}

    public TicketDto(Long id, String issue, String status, Long userId) {
        this.id = id;
        this.issue = issue;
        this.status = status;
        this.userId = userId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIssue() { return issue; }
    public void setIssue(String issue) { this.issue = issue; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}