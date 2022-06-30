package com.johnBryce.couponAppPhase2.controllers;

import com.johnBryce.couponAppPhase2.controllerVerifacationTools.Credentials;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
// works great

@RestController
@RequestMapping( "/login")
public class LoginController extends ClientController {

    public LoginController() {
        super();
    }



//
//    @RequestMapping(value = "/company-redirect" , method = RequestMethod.GET)
//    public ResponseEntity mirrorRest(@RequestBody(required = false) Credentials cred,
//                                     HttpMethod method, HttpServletRequest request, HttpServletResponse response)
//            throws URISyntaxException {
//        String requestUrl = request.getRequestURI();
//
//        URI uri = new URI("http", null, "localhost", 8888, "/company", null, null);
//        uri = UriComponentsBuilder.fromUri(uri)
//                .path(requestUrl)
//                .query(request.getQueryString())
//                .build(true).toUri();
//
//        HttpHeaders headers = new HttpHeaders();
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            headers.set(headerName, request.getHeader(headerName));
//        }
//
//        HttpEntity<Credentials> httpEntity = new HttpEntity<>(cred, headers);
//        RestTemplate restTemplate = new RestTemplate();
//        try {
//            return restTemplate.exchange(uri, method, httpEntity, String.class);
//        } catch(HttpStatusCodeException e) {
//            return ResponseEntity.status(e.getRawStatusCode())
//                    .headers(e.getResponseHeaders())
//                    .body(e.getResponseBodyAsString());
//        }
//    }





//    @RequestMapping("/company-redirect")
//public ResponseEntity<?> redirectToExternalUrl(@RequestBody Credentials cred) throws URISyntaxException {
//        URI companyPage = new URI("localhost8888/company");
//        if (companyService.login(cred.getEmail(), cred.getPassword(), cred.getRole())) {
//            HttpHeaders httpHeaders = new HttpHeaders();
//            String token = tokenManager.getNewToken();
//            httpHeaders.setLocation(companyPage);
//            return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
//        }
//        return new ResponseEntity<String>("Login error!", HttpStatus.BAD_REQUEST);
//    }

    @PostMapping( "/company-redirect")
    public ResponseEntity<?> CompanyLogin( Credentials cred) {
        System.out.println(new Date()+": Got a new login: "+cred);
        if (companyService.login(cred.getEmail(),cred.getPassword(),cred.getRole())) {
            String token = tokenManager.getNewToken();
            return new ResponseEntity<String>(token, HttpStatus.OK);
        }
        return new ResponseEntity<String>("Login error!", HttpStatus.BAD_REQUEST);
    }



    @PostMapping( "/customer-redirect")
        public ResponseEntity<?> CustomerLogin( Credentials cred)
        {
            System.out.println(new Date()+": Got a new login: "+cred);
            if (customerService.login(cred.getEmail(),cred.getPassword(),cred.getRole())) {
                String token = tokenManager.getNewToken();
                return new ResponseEntity<String>(token, HttpStatus.OK);
            }
            return new ResponseEntity<String>("Login error!", HttpStatus.BAD_REQUEST);
        }

    @PostMapping( "/admin-redirect")
    public ResponseEntity<?> AdminLogin( Credentials cred)
    {
        System.out.println(new Date()+": Got a new login: "+cred);
        if (adminService.login(cred.getEmail(),cred.getPassword(),cred.getRole())) {
            String token = tokenManager.getNewToken();
            return new ResponseEntity<String>( token, HttpStatus.OK);
        }
        else return new ResponseEntity<String>("Login error!", HttpStatus.BAD_REQUEST);
    }
}



//@RequestMapping("/to-be-redirected")
//public ResponseEntity<Object> redirectToExternalUrl() throws URISyntaxException {
//        URI yahoo = new URI("http://www.yahoo.com");
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setLocation(yahoo);
//        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
//        }

//
//    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
//    public void method(HttpServletResponse httpServletResponse) {
//        httpServletResponse.setHeader("Location", projectUrl);
//        httpServletResponse.setStatus(302);
//    }
//Second:
//
//@RequestMapping(value = "/redirect", method = RequestMethod.GET)
//public ModelAndView method() {
//        return new ModelAndView("redirect:" + projectUrl);
//        }

