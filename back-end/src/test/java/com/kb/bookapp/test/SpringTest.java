package com.kb.bookapp.test;

import com.kb.bookapp.BookAppApplication;
import com.kb.bookapp.app.configuration.APIClientConfiguration;
import com.kb.bookapp.app.configuration.SecurityConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {APIClientConfiguration.class, SecurityConfiguration.class, BookAppApplication.class})
public class SpringTest {

  @Before
  public void setUp() {

  }

  @Test
  public void empty() {

  }
}
