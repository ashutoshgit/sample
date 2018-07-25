package com.ashu.springbootproject.rest;

import com.ashu.springbootproject.model.Message;
import com.ashu.springbootproject.model.Response;
import com.ashu.springbootproject.service.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RestApiExample {

    @Autowired
    MyRepository myRepository;

    @RequestMapping(method = RequestMethod.GET, value="/msgs", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseEntity<Response> getMsgs() {
        ResponseEntity<Response> res;
        if (myRepository.getAll() != null || !myRepository.getAll().isEmpty()) {
            Response response = new Response("Success");
            response.setMsgList(myRepository.getAll());
            res = new ResponseEntity<Response>(response,HttpStatus.OK);
        } else {
            res = new ResponseEntity<Response>(new Response("Failed"), HttpStatus.NO_CONTENT);
        }
        return res;
    }

    @RequestMapping(method = RequestMethod.GET, value="/msg/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ResponseEntity<Response> getMsg(@PathVariable String id) {
        ResponseEntity<Response> responseEntity = null;
        if (myRepository.get(id) != null) {
            Response res = new Response("Success");
            res.getMsgList().add(myRepository.get(id));
            responseEntity = new ResponseEntity<Response>(res,HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<Response>(new Response("Failed"), HttpStatus.NO_CONTENT);
        }
        return responseEntity;
    }

    @RequestMapping(method = RequestMethod.POST, value="/save", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ResponseEntity<Response> saveMsg(@RequestBody Message message) {
        ResponseEntity<Response> responseEntity = null;
        if (message.getMessage() != null && myRepository.save(message.getId(),message.getMessage()).equalsIgnoreCase("Saved")) {
            Response res = new Response("Success");
            res.setMsgList(myRepository.getAll());
            responseEntity = new ResponseEntity<Response>(res, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<Response>(new Response("Failed"), HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
}
