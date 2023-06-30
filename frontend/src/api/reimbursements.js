import axios from "axios";
import { baseURL } from "../constants";

//GET all reimbursements
export const getAllReimbursements = async () => {
    const response = await axios.get(`${baseURL}/reimbursements`);
    console.log(response);
    return response;
}

//GET by employee
export const getReimbursementsByEmployeeId = async (id) => {
    const response = await axios.get(`${baseURL}/employees/${id}/reimbursements`);
    console.log(response);
    return response;
}

//CREATE new reimbursement, pass in object with price + description
export const createReimbursement = async (id, reimbursement) => {
    const token = sessionStorage.getItem("token");

    const response = await axios.post(
        `${baseURL}/reimbursements/${id}`,
        reimbursement,
        { headers: { Authorization: `Bearer ${token}`}}
    );

    console.log(response);
    return response;
};

//Resolve reimbursement, pass in reimbursement id + status object
export const resolveReimbursement = async (id, status) => {
    const token = sessionStorage.getItem("token");

    const response = await axios.put(
        `${baseURL}/reimbursements/${id}`,
        status,
        {headers: { Authorization: `Bearer ${token}`}}
    );

    console.log(response);
    return response;
} 