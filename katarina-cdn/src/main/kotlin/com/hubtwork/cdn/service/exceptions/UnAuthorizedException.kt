package com.hubtwork.cdn.service.exceptions

import com.hubtwork.cdn.service.exceptions.RiotException

class UnAuthorizedException(msg: String): RiotException(msg)