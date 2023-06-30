import { useEffect, useState } from "react";
import { getAllReimbursements, resolveReimbursement } from "../api/reimbursements";
import { Table, Container } from "react-bootstrap";
import axios from "axios";
import { getEmployeeByUsername } from "../api/auth";

export default function ManagerPage() {
    const [user, setUser] = useState({});
    const [reimbursements, setReimbursements] = useState([]);
    const [refresh, setRefresh] = useState(false);

    useEffect(() => {
        const fetch = async () => {
            const reimbursementData = await getAllReimbursements();
            const userData = await getEmployeeByUsername();

            console.log(userData);
            console.log(reimbursementData);
            setReimbursements(reimbursementData.data);
            setUser(userData.data);
        }
        fetch();
    }, [refresh])

    const handleResolve = async (e, id, option) => {
        e.preventDefault();

        const response = await resolveReimbursement(id, {state: option});
        console.log(response);

        setRefresh(!refresh);
    }

    return (
        <Container className="pageContainer">
            <h1>{user.firstName ? user.firstName + " - " + user.role.name : "Manager"}</h1>
            <Table variant="dark">
                <thead>
                    <tr>
                        <th>Price</th>
                        <th>Description</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        reimbursements.map((reimbursement, i) => {
                            if (reimbursement.id === 5 || reimbursement.status.state != "pending") {
                                return "";
                            }
                            return (
                                <tr key={i}>
                                    <td>{reimbursement.price}</td>
                                    <td>{reimbursement.description}</td>
                                    <td>
                                        {reimbursement.status.state}
                                    </td>
                                    <td>
                                        <button onClick={(e) => handleResolve(e, reimbursement.id, "approved")} type="button" style={{backgroundColor: "green", color: "white"}} className="rsBtn">
                                            Approve
                                        </button>
                                        <button onClick={(e) => handleResolve(e, reimbursement.id, "denied")} type="button" style={{backgroundColor: "red", color: "white"}} className="rsBtn mx-2">
                                            Deny
                                        </button>
                                    </td>
                                </tr>
                            )
                        })
                    }
                </tbody>
            </Table>
        </Container>
    )
}