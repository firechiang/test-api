package com.paipai.security.serializer;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

import java.util.List;

/**
 *
 * 将Null值序列化成指定值，使用方法如下，将CustomBeanSerializerModifier注册到 ObjectMapper
 *
 *  @PostConstruct
 *  public void registerObjectMapper() {
 *      SimpleModule module = new SimpleModule();
 *      module.setSerializerModifier(new CustomBeanSerializerModifier());
 *      objectMapper.registerModule(module);
 *  }
 *
 */
public class CustomBeanSerializerModifier extends BeanSerializerModifier {

    private final StringNullValueSerializer stringNullValueSerializer = new StringNullValueSerializer();
    private final NumberNullValueSerializer numberNullValueSerializer = new NumberNullValueSerializer();
    private final ListNullValueSerializer listNullValueSerializer = new ListNullValueSerializer();
    private final ObjectNullValueSerializer objectNullValueSerializer = new ObjectNullValueSerializer();


    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config,
                                                     BeanDescription beanDesc,
                                                     List<BeanPropertyWriter> beanProperties) {

        for (BeanPropertyWriter writer : beanProperties) {
            if (CharSequence.class.isAssignableFrom(writer.getType().getRawClass())) {
                writer.assignNullSerializer(stringNullValueSerializer);
            } else if (Number.class.isAssignableFrom(writer.getType().getRawClass())) {
                writer.assignNullSerializer(numberNullValueSerializer);
            } else if (List.class.isAssignableFrom(writer.getType().getRawClass())) {
                writer.assignNullSerializer(listNullValueSerializer);
            } else {
                writer.assignNullSerializer(objectNullValueSerializer);
            }
        }
        return beanProperties;
    }
}
