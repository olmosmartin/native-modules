import React from 'react';
import {NativeModules, Button} from 'react-native';

const CalendarButton = () => {
    const {CalendarModule} = NativeModules;
    const onPress = () => {
        CalendarModule.createCalendarEvent('testName', 'testLocation');
        CalendarModule.showDatePicker()
    };

    return (
        <Button
        title="Invocar calendario nativo"
        color="#841584"
        onPress={onPress}
        />
    );
};

export default CalendarButton;