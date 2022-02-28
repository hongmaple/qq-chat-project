package com.buleqr.dto.request;

import com.buleqr.pojo.PageDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author haiyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class EvaluationQuery extends PageDomain {
    /**
     * 作品id
     */
    private Long workId;
}
