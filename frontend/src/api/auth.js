import axios from "axios";
import { baseURL } from "../constants";

//userData should be object with username + password
export const handleLogin = async (userData) => {
    const response = await axios.post(
        `${baseURL}/auth/login`,
        userData
        );

    console.log(response);
    return response;
};

//userData should be object with firstName, lastName, username, password, role
export const handleRegister = async (userData) => {
    const response = await axios.post(
        `${baseURL}/auth/register`,
        userData
    )

    console.log(response);
    return response;
};

export const getEmployeeByUsername = async () => {
    const username = sessionStorage.getItem("username");
    
    const response = await axios.get(
        `${baseURL}/employees/current/${username}`);
    
    console.log(response);
    return response;
}