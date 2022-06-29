package com.johnBryce.couponAppPhase2.dailyJob;

import com.johnBryce.couponAppPhase2.repositories.CompanyRepository;
import com.johnBryce.couponAppPhase2.repositories.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
@Scope("singleton")
public class CouponExpirationDailyJob {
    private static final long sleepForADay = 86400000L;
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CompanyRepository companyRepository;

    static private boolean quit = false;

     private LocalDate now = LocalDate.now();
     private Thread t = new Thread(new Runnable() {
        @Override
        public void run()  {
              try {
                System.out.println("The job thread is running");
                while (!quit) {
                    timeLoop();
                }
                System.out.println("The job thread is now stopped");
              } catch (Exception e) {
                  System.out.println(e.toString() + "\n" +
                          "null problem in job");
              }
        }

    });

    public CouponExpirationDailyJob() {
        super();
    }



     public void start() {
        t.setDaemon(true);
        t.start();
    }


     public synchronized void stop() {
        quit = true;
    }
//    @Scheduled(timeUnit = TimeUnit.DAYS, fixedRate = 1)
    public void timeLoop()  {

        try {
//
            System.out.println(couponRepository.deleteByEndDateBefore(Date.valueOf(now)));
//
//           }
//
            try {
                Thread.sleep(sleepForADay);
            }catch (InterruptedException e){
                System.out.println("job sleep interupted");
            }

            } catch (Exception e) {
                System.out.println(e.toString() + "\n" +
                        " problem from job");

            }

    }


}
