package com.hubtwork.cdn.service.exceptions

import com.hubtwork.cdn.service.exceptions.RiotException

class ServiceUnavailableException(msg: String): RiotException(msg)