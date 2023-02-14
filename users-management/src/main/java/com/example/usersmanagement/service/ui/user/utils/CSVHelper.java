package com.example.usersmanagement.service.ui.user.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.example.usersmanagement.service.ui.user.dto.CreateUserDTO;

/**
 * 
 * @author benedetto.cosentino
 *
 */
public class CSVHelper {
  public static String TYPE = ".csv";
  static String[] HEADERS = {"FirstName", "LastName", "Email" , "Address"};

  public static boolean hasCSVFormat(MultipartFile file) {

    if (!file.getOriginalFilename().endsWith(TYPE)) {
      return false;
    }

    return true;
  }

  public static List<CreateUserDTO> csvToUsers(InputStream is) {
    try  {
    	BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

    	Iterable<CSVRecord> records = CSVFormat.Builder.create()
    		      .setHeader(HEADERS)
    		      .setSkipHeaderRecord(true)
    		      .build()
    		      .parse(fileReader);
    	
      List<CreateUserDTO> users = new ArrayList<CreateUserDTO>();

      for (CSVRecord csvRecord : records) {
    	  CreateUserDTO user = new CreateUserDTO(
              csvRecord.get("FirstName"),
              csvRecord.get("LastName"),
              csvRecord.get("Email"),
              csvRecord.get("Address")
            );

    	  users.add(user);
      }

      return users;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }

}