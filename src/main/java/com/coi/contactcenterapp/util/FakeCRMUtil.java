package com.coi.contactcenterapp.util;

import com.coi.contactcenterapp.domain.dto.calling.Contact_DTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeCRMUtil {
    public static Map<String, Contact_DTO> contacts;

    static {
        contacts = new HashMap<>();
        contacts.put("contact_1", new Contact_DTO("contact_1", "+79136552087", "Фабер Евгений Александрович", "", "COLD", "NEW"));
        contacts.put("contact_2", new Contact_DTO("contact_2", "+79136522087", "Щербаков Фёдор Филиппович", "", "COLD", "NEW"));
        contacts.put("contact_3", new Contact_DTO("contact_3", "+79116552087", "Уткина Дарья Тимуровна", "", "COLD", "NEW"));
        contacts.put("contact_4", new Contact_DTO("contact_4", "+79136552086", "Баранов Владимир Даниилович", "", "COLD", "NEW"));
        contacts.put("contact_5", new Contact_DTO("contact_5", "+79134552085", "Игнатова Арина Владиславовна", "", "COLD", "NEW"));
        contacts.put("contact_6", new Contact_DTO("contact_6", "+79136552084", "Смирнова Ксения Артёмовна", "", "COLD", "NEW"));
        contacts.put("contact_7", new Contact_DTO("contact_7", "+79136552083", "Леонов Даниил Викторович", "", "COLD", "NEW"));
        contacts.put("contact_8", new Contact_DTO("contact_8", "+79136552082", "Медведев Иван Богданович", "", "INFO", "NEW"));
        contacts.put("contact_9", new Contact_DTO("contact_9", "+79136552081", "Дружинин Алексей Александрович", "", "INFO", "NEW"));
        contacts.put("contact_10", new Contact_DTO("contact_10", "+79236552087", "Лазарева София Егоровна", "", "INFO", "NEW"));
        contacts.put("contact_11", new Contact_DTO("contact_11", "+79336552087", "Семенова Ксения Захаровна", "", "INFO", "NEW"));
        contacts.put("contact_12", new Contact_DTO("contact_12", "+79436552087", "Маркова Елизавета Дмитриевна", "", "INFO", "NEW"));
        contacts.put("contact_13", new Contact_DTO("contact_13", "+79536552087", "Лаврентьев Георгий Вячеславович", "", "INFO", "NEW"));
    }
}
