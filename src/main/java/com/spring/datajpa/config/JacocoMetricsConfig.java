package com.spring.datajpa.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Gauge;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;

@Configuration
public class JacocoMetricsConfig {

    private final MeterRegistry meterRegistry;

    public JacocoMetricsConfig(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @PostConstruct
    public void init() {
        // Mengambil angka dari Environment Variable sistem
        String envCoverage = System.getenv("JACOCO_COVERAGE_VALUE");
        final double coveragePercent = (envCoverage != null) ? Double.parseDouble(envCoverage) : 0.0;

        // Daftarkan langsung ke Prometheus Actuator
        Gauge.builder("jacoco_line_coverage_percent", () -> coveragePercent)
                .description("JaCoCo Line Coverage Percentage from CI env")
                .tag("application", "employee-management")
                .register(meterRegistry);
    }
}
