import { Container } from "react-bootstrap";
import { Outlet } from "react-router-dom";
import { useNavigate } from "react-router-dom";

export default function Layout() {
    const navigate = useNavigate();

    const handleLogout = (e) => {
        e.preventDefault();
        sessionStorage.clear();
        navigate("/auth");
    }

    return (
        <Container className="pageContainer">
            <button onClick={handleLogout} className="mt-3 rsBtn" style={{alignSelf: "flex-end"}} type="button">Log out</button>
            <h1>Welcome to Reimbursement System</h1>
            <Outlet />
        </Container>
    )
}