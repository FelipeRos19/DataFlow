package dev.feliperos.utils;

import dev.feliperos.core.exceptions.InvalidTypeException;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.params.GetExParams;

public class TimeProcessor {
    public static GetExParams processGetExTimeUnit(Protocol.Keyword type, long time) throws InvalidTypeException {
        GetExParams params = new GetExParams();
        switch (type) {
            case EX -> params.ex(time);
            case PX -> params.px(time);
            case EXAT -> params.exAt(time);
            case PXAT -> params.pxAt(time);
            case PERSIST -> params.persist();
            default -> throw new InvalidTypeException();
        }
        return params;
    }
}
