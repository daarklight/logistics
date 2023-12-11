package com.tsystems.logistics.logistics_vp.exceptions;

import com.tsystems.logistics.logistics_vp.exceptions.custom.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@ControllerAdvice
@Log4j2
public class GlobalErrorHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorRepresentation> handleNoSuchElementException(NoSuchElementException exception) {
        String message = "No such element in the database. Please try another one";
        log.error(message);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorRepresentation.builder()
                                .error("error.technical.01")
                                .message(message)
                                .dateAndTime(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorRepresentation> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException exception) {
        String message = "Incorrect data type. Please input correct data";
        log.error(message);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorRepresentation.builder()
                                .error("error.technical.02")
                                .message(message)
                                .dateAndTime(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(GoogleMapsServiceException.class)
    public ResponseEntity<ErrorRepresentation> handleGoogleMapsServiceException(
            GoogleMapsServiceException exception) {
        String message = "Problems with Google Maps Service. Please try later or ask your admin to help";
        log.error(message);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ErrorRepresentation.builder()
                                .error("error.technical.03")
                                .message(message)
                                .dateAndTime(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(NoSuchCargoException.class)
    public ResponseEntity<ErrorRepresentation> handleNoSuchCargoException(
            NoSuchCargoException exception) {
        String message = "No such cargo in the database. Please try input another one cargo";
        log.error(message);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorRepresentation.builder()
                                .error("error.technical.04")
                                .message(message)
                                .dateAndTime(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(NoSuchCustomerException.class)
    public ResponseEntity<ErrorRepresentation> handleNoSuchCustomerException(
            NoSuchCustomerException exception) {
        String message = "No such customer in the database. Please try input another one customer";
        log.error(message);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorRepresentation.builder()
                                .error("error.technical.05")
                                .message(message)
                                .dateAndTime(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(NoSuchDriverException.class)
    public ResponseEntity<ErrorRepresentation> handleNoSuchDriverException(
            NoSuchDriverException exception) {
        String message = "No such driver in the database. Please try input another one driver";
        log.error(message);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorRepresentation.builder()
                                .error("error.technical.06")
                                .message(message)
                                .dateAndTime(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(NoSuchLogisticianException.class)
    public ResponseEntity<ErrorRepresentation> handleNoSuchLogisticianException(
            NoSuchLogisticianException exception) {
        String message = "No such logistician in the database. Please try input another one logistician";
        log.error(message);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorRepresentation.builder()
                                .error("error.technical.06")
                                .message(message)
                                .dateAndTime(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(NoSuchOrderException.class)
    public ResponseEntity<ErrorRepresentation> handleNoSuchOrderException(
            NoSuchOrderException exception) {
        String message = "No such order in the database. Please try input another one order";
        log.error(message);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorRepresentation.builder()
                                .error("error.technical.07")
                                .message(message)
                                .dateAndTime(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(NoSuchTruckException.class)
    public ResponseEntity<ErrorRepresentation> handleNoSuchTruckException(
            NoSuchTruckException exception) {
        String message = "No such truck in the database. Please try input another one truck";
        log.error(message);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorRepresentation.builder()
                                .error("error.technical.08")
                                .message(message)
                                .dateAndTime(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(NoSuchAuthenticationInfoException.class)
    public ResponseEntity<ErrorRepresentation> handleNoSuchAuthenticationInfoException(
            NoSuchAuthenticationInfoException exception) {
        String message = "No such authentication info the database. Please try input another one " +
                "authentication info";
        log.error(message);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorRepresentation.builder()
                                .error("error.technical.08")
                                .message(message)
                                .dateAndTime(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(TooManyCargosException.class)
    public ResponseEntity<ErrorRepresentation> handleTooManyCargosException(
            TooManyCargosException exception) {
        String message = "Maximum number of cargos for one order is limited by two";
        log.error(message);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorRepresentation.builder()
                                .error("error.business.01")
                                .message(message)
                                .dateAndTime(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(TooManyDriversException.class)
    public ResponseEntity<ErrorRepresentation> handleTooManyDriversException(
            TooManyDriversException exception) {
        String message = "Maximum number of drivers for one order is limited by two";
        log.error(message);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorRepresentation.builder()
                                .error("error.business.02")
                                .message(message)
                                .dateAndTime(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(UnloadingNonLoadedCargoException.class)
    public ResponseEntity<ErrorRepresentation> handleUnloadingNonLoadedCargoException(
            UnloadingNonLoadedCargoException exception) {
        String message = "Non-loaded cargo cannot be unloaded. Please load cargo before";
        log.error(message);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorRepresentation.builder()
                                .error("error.business.03")
                                .message(message)
                                .dateAndTime(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(TooHeavyCargosException.class)
    public ResponseEntity<ErrorRepresentation> handleTooHeavyCargosException(
            TooHeavyCargosException exception) {
        String message = "Total weight of cargos for order exceeds max limit capacity 22 tons for any of our trucks";
        log.error(message);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorRepresentation.builder()
                                .error("error.business.04")
                                .message(message)
                                .dateAndTime(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(NonProperTruckCapacityException.class)
    public ResponseEntity<ErrorRepresentation> handleNonProperTruckCapacityException(
            NonProperTruckCapacityException exception) {
        String message = "Order exceeds max capacity of this truck. Please try to search another one truck with higher capacity";
        log.error(message);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorRepresentation.builder()
                                .error("error.business.05")
                                .message(message)
                                .dateAndTime(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(NoProperTrucksException.class)
    public ResponseEntity<ErrorRepresentation> handleNoProperTrucksException(
            NoProperTrucksException exception) {
        String message = "No proper trucks were found for this order. Please consider registering new trucks";
        log.error(message);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorRepresentation.builder()
                                .error("error.business.06")
                                .message(message)
                                .dateAndTime(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(NoProperDriversException.class)
    public ResponseEntity<ErrorRepresentation> handleNoProperDriversException(
            NoProperDriversException exception) {
        String message = "No proper drivers were found for this order. Please consider registering new drivers";
        log.error(message);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorRepresentation.builder()
                                .error("error.business.07")
                                .message(message)
                                .dateAndTime(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(IncorrectCargoStartPointException.class)
    public ResponseEntity<ErrorRepresentation> handleIncorrectCargoStartPointException(
            IncorrectCargoStartPointException exception) {
        String message = "Start point (address, city and state) for second cargo cannot differ from first cargo " +
                "of the same order";
        log.error(message);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorRepresentation.builder()
                                .error("error.business.08")
                                .message(message)
                                .dateAndTime(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(ImpossibleCargoUpdateException.class)
    public ResponseEntity<ErrorRepresentation> handleImpossibleCargoUpdateException(
            ImpossibleCargoUpdateException exception) {
        String message = "It is impossible to update cargo when order status is not equal to NEW or DECLINED_BY_DRIVERS";
        log.error(message);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorRepresentation.builder()
                                .error("error.business.09")
                                .message(message)
                                .dateAndTime(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(ImpossibleOrderUpdateException.class)
    public ResponseEntity<ErrorRepresentation> handleImpossibleOrderUpdateException(
            ImpossibleOrderUpdateException exception) {
        String message = "It is impossible to update order when order status is not equal to NEW or DECLINED_BY_DRIVERS";
        log.error(message);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorRepresentation.builder()
                                .error("error.business.10")
                                .message(message)
                                .dateAndTime(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(BusyDriverException.class)
    public ResponseEntity<ErrorRepresentation> handleBusyDriverException(
            BusyDriverException exception) {
        String message = "It is impossible to assign busy driver. Please search another one driver";
        log.error(message);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorRepresentation.builder()
                                .error("error.business.11")
                                .message(message)
                                .dateAndTime(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(BusyTruckException.class)
    public ResponseEntity<ErrorRepresentation> handleBusyTruckException(
            BusyTruckException exception) {
        String message = "It is impossible to assign busy truck. Please search another one truck";
        log.error(message);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorRepresentation.builder()
                                .error("error.business.12")
                                .message(message)
                                .dateAndTime(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(NotEnoughDriverHoursException.class)
    public ResponseEntity<ErrorRepresentation> handleNotEnoughDriverHoursException(
            NotEnoughDriverHoursException exception) {
        String message = "Driver does not have enough working hours in this month. Please choose two drivers or " +
                "choose one driver with less working hours. You also can try to postpone the order if it is possible";
        log.error(message);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorRepresentation.builder()
                                .error("error.business.13")
                                .message(message)
                                .dateAndTime(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(GoogleMapsIncorrectDataException.class)
    public ResponseEntity<ErrorRepresentation> handleGoogleMapsIncorrectDataException(
            GoogleMapsIncorrectDataException exception) {
        String message = "Problems with Google Maps Service. It seems that city/state/address can be incorrect. " +
                "Please check if there are any typos";
        log.error(message);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ErrorRepresentation.builder()
                                .error("error.business.14")
                                .message(message)
                                .dateAndTime(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(NoAssignedTruckException.class)
    public ResponseEntity<ErrorRepresentation> handleNoAssignedTruckException(
            NoAssignedTruckException exception) {
        String message = "It is impossible to assign driver before truck assign. Please firstly assign truck";
        log.error(message);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ErrorRepresentation.builder()
                                .error("error.business.15")
                                .message(message)
                                .dateAndTime(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(ImpossibleCargoDeleteException.class)
    public ResponseEntity<ErrorRepresentation> handleImpossibleCargoDeleteException(
            ImpossibleCargoDeleteException exception) {
        String message = "It is impossible to delete cargo when order status is not equal to NEW or DECLINED_BY_DRIVERS";
        log.error(message);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorRepresentation.builder()
                                .error("error.business.16")
                                .message(message)
                                .dateAndTime(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(ImpossibleBusyTruckDeleteException.class)
    public ResponseEntity<ErrorRepresentation> handleImpossibleBusyTruckDeleteException(
            ImpossibleBusyTruckDeleteException exception) {
        String message = "It is impossible to delete busy truck";
        log.error(message);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorRepresentation.builder()
                                .error("error.business.17")
                                .message(message)
                                .dateAndTime(LocalDateTime.now())
                                .build());
    }

    @ExceptionHandler(ImpossibleBusyDriverDeleteException.class)
    public ResponseEntity<ErrorRepresentation> handleImpossibleBusyDriverDeleteException(
            ImpossibleBusyDriverDeleteException exception) {
        String message = "It is impossible to delete busy driver";
        log.error(message);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorRepresentation.builder()
                                .error("error.business.18")
                                .message(message)
                                .dateAndTime(LocalDateTime.now())
                                .build());
    }
}
