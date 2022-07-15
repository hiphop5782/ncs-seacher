import React, {useState} from 'react';
import { Container, Grid, TextField, List, ListItem, ListItemText, ListItemAvatar, Avatar } from '@mui/material';
import ImageIcon from '@mui/icons-material/Image';
import './App.css';

function App(){
  const [keyword, setKeyword] = useState("");
  const moduleList = [
    {
      name:"애플리케이션 설계",
      main:"20010101_19v4"
    },
    {
      name:"애플리케이션 배포",
      main:"20010102_19v4"
    },
    {
      name:"애플리케이션 구현",
      main:"20010103_19v4"
    },
  ]

  let filterList = [];
  const search = ()=>{
    filterList = moduleList.filter(m=>m.name.indexOf(keyword) >= 0);
  };
  const change = (e)=>{
    setKeyword(e.target.value);
    search();
  };

  return (
  <Container maxWidth="lg">

    <Grid container marginTop={3}>
      <Grid item xs={12}>
        <TextField fullWidth onInput={change}></TextField>
      </Grid>
    </Grid>

    <Grid container marginTop={3}>
      <Grid item xs={12}>
        '{keyword}' 에 대한 검색 결과 ({filterList.length}개)
      </Grid>
    </Grid>

    <Grid container marginTop={3}>
      <Grid item xs={12}>
        <List sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
          {filterList.map(m=>(
            <ListItem key={m.main}>
              <ListItemAvatar>
                <Avatar>
                  <ImageIcon />
                </Avatar>
              </ListItemAvatar>
              <ListItemText primary={m.name} secondary={m.main} />
            </ListItem>
          ))}
        </List>
      </Grid>
    </Grid>

  </Container>
  )
}

export default App;
