package start.spring.io.buyacar.service;

import java.util.List;

public interface IConvertingData {
    <T> T getData (String json, Class<T> classes);
    <T>List <T>  getList(String json, Class<T> classes);
}
