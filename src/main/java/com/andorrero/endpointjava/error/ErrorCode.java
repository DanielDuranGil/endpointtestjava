package com.andorrero.endpointjava.error;

import java.io.Serializable;

public interface ErrorCode extends Serializable {

    String getReasonPhrase();

}
