// import { useNavigate } from "react-router-dom";
// import '../PatientDashboard/PatientDashboard.css';

// const PatientDashboard = () => {
//   const navigate = useNavigate();
//   const handleLogoClick = () => {
//     navigate("/");
//   };

//   return (
//     <div className="dashboard">
//       <nav className="dashboard-nav">
//         <div className="logo-container" onClick={handleLogoClick}>
//           <img src={"https://source.unsplash.com/100x100?dental,logo"} alt="Company Logo" className="company-logo" />
//           <h1 className="company-title">United Dental Surgeries</h1>
//         </div>
//       </nav>
//       <div className="content">
//         <h1>Welcome to your Patient Dashboard</h1>
//         <p>What would you like to do today?</p>
//         <div className="dashboard-buttons">
//           <button className="dashboard-button" onClick={()=>navigate("/patient/appointments")}>Manage Appointments</button>
//           <button className="dashboard-button" onClick={()=>navigate("/bills")}>View Bills</button>
//           <button className="dashboard-button" onClick={()=>navigate("/medicalrecords")}>View Medical Records</button>
//         </div>
//       </div>
//     </div>
//   );
// }

// export default PatientDashboard;


import { useNavigate } from "react-router-dom";
import styled from "styled-components";

const Dashboard = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  height: 100vh;
  background-color: #f5f5f5;
  padding-top: 50px;
`;

const DashboardNav = styled.nav`
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  padding-bottom: 50px;
  box-shadow: 0 2px 4px 0 rgba(0,0,0,0.1);
`;

const LogoContainer = styled.div`
  display: flex;
  align-items: center;
  cursor: pointer;
`;

const CompanyLogo = styled.img`
  margin-right: 10px;
`;

const CompanyTitle = styled.h1`
  color: #333;
  font-size: 1.5em;
`;

const Content = styled.div`
  width: 80%;
  text-align: center;
`;

const DashboardButtons = styled.div`
  display: flex;
  justify-content: space-between;
  margin-top: 50px;
`;

const DashboardButton = styled.button`
  flex: 1;
  margin: 0 10px;
  padding: 15px;
  font-size: 1em;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  text-align: center;

  &:hover {
    background-color: #0056b3;
  }
`;

const PatientDashboard = () => {
  const navigate = useNavigate();
  const handleLogoClick = () => {
    navigate("/");
  };

  return (
    <Dashboard>
      <DashboardNav>
        <LogoContainer onClick={handleLogoClick}>
          <CompanyLogo src={"https://source.unsplash.com/100x100?dental,logo"} alt="Company Logo" />
          <CompanyTitle>United Dental Surgeries</CompanyTitle>
        </LogoContainer>
      </DashboardNav>
      <Content>
        <h1>Welcome to your Patient Dashboard</h1>
        <p>What would you like to do today?</p>
        <DashboardButtons>
          <DashboardButton onClick={()=>navigate("/patient/appointments")}>Manage Appointments</DashboardButton>
          <DashboardButton onClick={()=>navigate("/patient/bills")}>View Bills</DashboardButton>
          <DashboardButton onClick={()=>navigate("/patient/medicalrecords")}>View Medical Records</DashboardButton>
        </DashboardButtons>
      </Content>
    </Dashboard>
  );
}

export default PatientDashboard;
