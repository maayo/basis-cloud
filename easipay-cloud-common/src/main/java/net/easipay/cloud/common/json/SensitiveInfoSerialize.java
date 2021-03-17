package net.easipay.cloud.common.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.easipay.cloud.annotaion.SensitiveInfo;
import net.easipay.cloud.common.utils.SensitiveInfoUtils;
import net.easipay.cloud.enums.SensitiveType;

import java.io.IOException;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
public class SensitiveInfoSerialize extends JsonSerializer<String> implements ContextualSerializer {

	private SensitiveType type;

	@Override
	public void serialize(final String s, final JsonGenerator jsonGenerator,
						  final SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
		switch (this.type) {
			case CHINESE_NAME: {
				jsonGenerator.writeString(SensitiveInfoUtils.chineseName(s));
				break;
			}
			case ID_CARD: {
				jsonGenerator.writeString(SensitiveInfoUtils.idCardNum(s));
				break;
			}
			case FIXED_PHONE: {
				jsonGenerator.writeString(SensitiveInfoUtils.fixedPhone(s));
				break;
			}
			case MOBILE_PHONE: {
				jsonGenerator.writeString(SensitiveInfoUtils.mobilePhone(s));
				break;
			}
			case ADDRESS: {
				jsonGenerator.writeString(SensitiveInfoUtils.address(s, 4));
				break;
			}
			case EMAIL: {
				jsonGenerator.writeString(SensitiveInfoUtils.email(s));
				break;
			}
			case BANK_CARD: {
				jsonGenerator.writeString(SensitiveInfoUtils.bankCard(s));
				break;
			}
			case CNAPS_CODE: {
				jsonGenerator.writeString(SensitiveInfoUtils.cnapsCode(s));
				break;
			}
		}

	}

	@Override
	public JsonSerializer<?> createContextual(final SerializerProvider serializerProvider,
											  final BeanProperty beanProperty) throws JsonMappingException {
		if (beanProperty != null) { // 为空直接跳过
			if (Objects.equals(beanProperty.getType().getRawClass(), String.class)) { // 非 String 类直接跳过
				SensitiveInfo sensitiveInfo = beanProperty.getAnnotation(SensitiveInfo.class);
				if (sensitiveInfo == null) {
					sensitiveInfo = beanProperty.getContextAnnotation(SensitiveInfo.class);
				}
				if (sensitiveInfo != null) { // 如果能得到注解，就将注解的 value 传入 SensitiveInfoSerialize

					return new SensitiveInfoSerialize(sensitiveInfo.value());
				}
			}
			return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
		}
		return serializerProvider.findNullValueSerializer(beanProperty);
	}
}