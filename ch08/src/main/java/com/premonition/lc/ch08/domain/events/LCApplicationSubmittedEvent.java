package com.premonition.lc.ch08.domain.events;

import com.premonition.lc.ch08.domain.LCApplicationId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LCApplicationSubmittedEvent {
    private LCApplicationId id;
}
