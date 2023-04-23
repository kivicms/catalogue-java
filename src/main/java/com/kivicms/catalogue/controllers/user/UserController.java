package com.kivicms.catalogue.controllers.user;

import com.google.common.base.Joiner;
import com.kivicms.catalogue.controllers.user.payload.RegisterPayload;
import com.kivicms.catalogue.filters.SearchCriteria;
import com.kivicms.catalogue.filters.SearchOperation;
import com.kivicms.catalogue.filters.user.UserSpecificationsBuilder;
import com.kivicms.catalogue.models.catalogue.Catalogue;
import com.kivicms.catalogue.models.user.User;
import com.kivicms.catalogue.repository.user.UserRepository;
import com.kivicms.catalogue.service.user.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
@RequestMapping("/api/v1/users")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/index")
    public ResponseEntity<?> register(@RequestBody User user) {
        return userService.update(user);
    }

    @GetMapping(value = "/search-spec")
    @ResponseBody
    public List<User> searchSpec(@RequestParam(value = "search") String search) {
        UserSpecificationsBuilder builder = new UserSpecificationsBuilder();
        String operationSetExper = Joiner.on("|")
                .join(SearchOperation.SIMPLE_OPERATION_SET);
        Pattern pattern = Pattern.compile("(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\S+?)(\\p{Punct}?),", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(search + ",");
        log.info("operationSetExper: " + "(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\S+?)(\\p{Punct}?),");
        while (matcher.find()) {
            log.info("SEARCH "  + matcher.group(1) + "-" + matcher.group(2) + "-" + matcher.group(4)+ "-" + matcher.group(3)+ "-" + matcher.group(5));
            builder.with(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5));
        }

        Specification<User> spec = builder.build();
        return userRepository.findAll(spec);
    }

/*    @RequestMapping(method = RequestMethod.GET, value = "/search")
    @ResponseBody
    public List<User> search(@RequestParam(value = "search", required = false) String search) {
        List<SearchCriteria> params = new ArrayList<SearchCriteria>();
        if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
            Matcher matcher = pattern.matcher(search + ",");
            log.info("SEARCH!!!!! " + search, matcher);
            while (matcher.find()) {
                log.info("SEARCH "  + matcher.group(1) + " " + matcher.group(2) + " " + matcher.group(3));
                params.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
            }
        }
        return userService.searchUser(params);
    }*/

    @GetMapping("/generate")
    public ResponseEntity<List<User>> generate(Integer number) {
        return ResponseEntity.ok(userService.generate(number));
    }
}
