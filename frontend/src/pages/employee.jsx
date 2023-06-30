import { useEffect, useState } from "react"
import { getEmployeeByUsername } from "../api/auth"
import { 
    Container, Table, Form,
    Row, Col
} from "react-bootstrap";
import { createReimbursement } from "../api/reimbursements";

export default function EmployeePage() {
    const [user, setUser] = useState({});
    const [reimbursements, setReimbursements] = useState([]);
    const [refresh, setRefresh] = useState(false);
    const [showAdd, setShowAdd] = useState(false);
    const [price, setPrice] = useState();
    const [description, setDescription] = useState("");

    useEffect(() => {
        const fetchUser = async () => {
            const userData = await getEmployeeByUsername();
            console.log(userData);
            setUser(userData.data);
            setReimbursements(userData.data.reimbursements);
        }

        fetchUser();
    }, [refresh]);

    const handleAdd = async () => {
        if (showAdd === true && price != null && description != "") {
            const reimbursement = {price: price, description: description};

            const newReimbursement = await createReimbursement(user.id, reimbursement);

            console.log(newReimbursement);
            setPrice(null);
            setDescription("");
            setRefresh(!refresh);
            setShowAdd(false);
        } else if (showAdd === true && price === null || description === "") {
            console.log("Invalid price/description!");
            setPrice(null);
            setDescription("");
        }

        setShowAdd(!showAdd)
    }

    return (
        <Container className="pageContainer">
            <h1 className="mb-3">{user.firstName ? user.firstName + " - " + user.role.name : "Employee"}</h1>

            <button className="mb-3 rsBtn" onClick={handleAdd} type="button">{showAdd ? "Submit" : "Add New"}</button>
            {
                showAdd ?
                <Row className="mb-5">
                    <Col><Form.Control onChange={(e) => setPrice(parseInt(e.target.value))} type="number" placeholder="Enter price"></Form.Control></Col>
                    <Col><Form.Control onChange={(e) => setDescription(e.target.value)} type="text" placeholder="Enter description"></Form.Control></Col>
                </Row>
                : ""
            }
            <Table variant="dark">
                <thead>
                    <tr>
                        <th>Price</th>
                        <th>Description</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        reimbursements.map((reimbursement, i) => {
                            if (reimbursement.id === 5) {return ""};
                            if (reimbursement.status.state === "pending") {
                                return (
                                    <tr key={i}>
                                        <td>{reimbursement.price}</td>
                                        <td>{reimbursement.description}</td>
                                        <td>{reimbursement.status.state}</td>
                                    </tr>
                                )
                            }
                        })
                    }
                    {
                        reimbursements.map((reimbursement, i) => {
                            if (reimbursement.id === 5) {return ""};
                            if (reimbursement.status.state != "pending") {
                                return (
                                    <tr key={i}>
                                        <td>{reimbursement.price}</td>
                                        <td>{reimbursement.description}</td>
                                        <td>{reimbursement.status.state}</td>
                                    </tr>
                                )
                            }
                        })
                    }
                </tbody>
            </Table>
        </Container>
    )
}