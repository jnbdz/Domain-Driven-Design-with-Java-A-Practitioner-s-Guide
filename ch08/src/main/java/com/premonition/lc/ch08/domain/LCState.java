package com.premonition.lc.ch08.domain;

public enum LCState {
    DRAFT,
    SUBMITTED,
    APPROVED, REJECTED;

    public boolean isNotDraft() {
        return this != DRAFT;
    }

    public boolean isDraft() {
        return this == DRAFT;
    }

}
