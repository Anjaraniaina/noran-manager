package me.noran.manager.controller;

import me.noran.manager.config.CompanyConf;
import me.noran.manager.controller.mapper.EmployeeMapper;
import me.noran.manager.controller.validator.EmployeeValidator;
import me.noran.manager.model.Employee;
import me.noran.manager.model.EmployeeFilter;
import me.noran.manager.model.enums.BirthDateOption;
import me.noran.manager.service.CSVUtils;
import me.noran.manager.service.EmployeeService;
import me.noran.manager.service.PDFService;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.List;


@Controller
@AllArgsConstructor
@RequestMapping("/server/employee")
public class EmployeeController {
    private EmployeeMapper employeeMapper;
    private EmployeeValidator employeeValidator;
    private EmployeeService employeeService;
    private PDFService pdfService;

    @GetMapping("/list/csv")
    public ResponseEntity<byte[]> getCsv(HttpSession session) {
        EmployeeFilter filters = (EmployeeFilter) session.getAttribute("employeeFiltersSession");
        List<Employee> data = employeeService.getAll(filters).stream().map(employeeMapper::toView).toList();

        String csv = CSVUtils.convertToCSV(data);
        byte[] bytes = csv.getBytes();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setContentDispositionFormData("attachment", "employees.csv");
        headers.setContentLength(bytes.length);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    @GetMapping("/list/filters/clear")
    public String clearFilters(HttpSession session) {
        session.removeAttribute("employeeFilters");
        return "redirect:/employee/list";
    }

    @PostMapping("/createOrUpdate")
    public String saveOne(@ModelAttribute Employee employee) {
        employeeValidator.validate(employee);
        me.noran.manager.repository.entity.Employee domain = employeeMapper.toDomain(employee);
        employeeService.saveOne(domain);
        return "redirect:/employee/list";
    }

    @GetMapping("/payslip/{eId}")
    public ResponseEntity<byte[]> generatePaySlipPdf(@PathVariable String eId, @RequestParam(defaultValue = "BIRTHDAY") String ageOption) throws DocumentException, IOException {
        BirthDateOption birthDateOption = BirthDateOption.valueOf(ageOption);
        Employee toShow = employeeMapper.toView(employeeService.getOne(eId), birthDateOption);

        Context context = new Context();
        context.setVariable("employee", toShow);
        context.setVariable("companyConf", new CompanyConf());

        byte[] pdfBytes = pdfService.generatePdfFromTemplate("payslip", context);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "payslip.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(pdfBytes.length)
                .body(pdfBytes);
    }
}
