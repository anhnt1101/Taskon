package com.example.demo.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class WebDriverTasonService {

    public String runSeleniumScript(String link) {
        // Đường dẫn tới ChromeDriver, cần đảm bảo đây là đường dẫn chính xác tới tệp chromedriver.exe
        System.setProperty("webdriver.chrome.driver", "chromedriver-win64/chromedriver-win64/chromedriver.exe");

        // Danh sách các URL cần mở
        List<String> urls = List.of(
                link
//                "https://taskon.xyz/campaign/detail/285033793",
//                "https://taskon.xyz/campaign/detail/87346575",
//                "https://taskon.xyz/campaign/detail/135142575",
//                "https://taskon.xyz/campaign/detail/169208662",
//                "https://taskon.xyz/campaign/detail/257655942",
//                "https://taskon.xyz/campaign/detail/540993944",
//                "https://taskon.xyz/campaign/detail/479547589",
//                "https://taskon.xyz/campaign/detail/167906917",
//                "https://taskon.xyz/campaign/detail/424150505",
//                "https://taskon.xyz/campaign/detail/168201226",
//                "https://taskon.xyz/campaign/detail/814999493",
//                "https://taskon.xyz/campaign/detail/75915866",
//                "https://taskon.xyz/campaign/detail/733457937",
//                "https://taskon.xyz/campaign/detail/431671961",
//                "https://taskon.xyz/campaign/detail/404633960",
//                "https://taskon.xyz/campaign/detail/257655942",
//                "https://taskon.xyz/campaign/detail/998334075"
        );

        // Khởi tạo trình duyệt Chrome
        WebDriver driver = new ChromeDriver();

        StringBuilder result = new StringBuilder();

        for (String url : urls) {
            // Mở URL
            driver.get(url);

            // Đợi một khoảng thời gian để trang web tải xong
            try {
                Thread.sleep(5000); // 5 giây
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Tìm thẻ div với class "time-range" và lấy nội dung của nó
            String timeRangeText = "";
            try {
                WebElement timeRangeElement = driver.findElement(By.cssSelector("div.time-range"));
                timeRangeText = timeRangeElement.getText();
                result.append("URL: ").append(url).append("\nTime Range: ").append(timeRangeText).append("\n\n");
            } catch (Exception e) {
                result.append("Không tìm thấy thẻ div với class 'time-range' ở URL: ").append(url).append("\n");
                e.printStackTrace();
            }

            // Lưu nội dung thời gian vào file ghi chú
            if (!timeRangeText.isEmpty()) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("TaiNguyen.txt", true))) {
                    writer.write("URL: " + url + "\nTime Range: " + timeRangeText + "\n\n");
                    System.out.println("Đã lưu thời gian vào file TaiNguyen.txt");
                } catch (IOException e) {
                    System.out.println("Lỗi khi ghi vào file");
                    e.printStackTrace();
                }
            }
        }

        // Đóng trình duyệt
        driver.quit();

        return result.toString();
    }

}
