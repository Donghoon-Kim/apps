package com.kb.bookapp.business.service;

import com.kb.bookapp.business.component.RsaTextBCryptPasswordEncoder;
import com.kb.bookapp.business.domain.Member;
import com.kb.bookapp.repository.MemberRepository;
import com.kb.bookapp.test.SpringTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class MemberServiceTest extends SpringTest {

  @Mock
  private MemberRepository memberRepository;

  @Mock
  private RsaTextBCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  @InjectMocks
  private MemberService service;

  @Before
  public void setUp() {
    super.setUp();
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testCreateMemberSuccess() throws Exception {
    // given
    final String encoded = "djienfcxl";
    final Member member = new Member();
    member.setUsername("test_new_member");
    member.setPassword("test123");
    final Member saved = new Member();
    saved.setUsername(member.getUsername());
    saved.setPassword(encoded);

    when(memberRepository.findByUsername(any(String.class))).thenReturn(null);
    when(bCryptPasswordEncoder.encode(any(CharSequence.class))).thenReturn(encoded);
    when(memberRepository.save(any(Member.class))).thenReturn(saved);

    // when
    Member result = service.createMember(member);

    //then
    assertThat(result.getUsername(), is(member.getUsername()));
    assertThat(result.getPassword(), is(encoded));
  }

  @Test(expected = RuntimeException.class)
  public void testCreateMemberFailBecauseMemberExist() throws Exception {
    final Member member = new Member();
    member.setUsername("test_new_member");
    member.setPassword("test123");

    when(memberRepository.findByUsername(any(String.class))).thenReturn(member);

    // when
    service.createMember(member);
  }

  @Test
  public void testLoadUserByUsernameSuccess() throws Exception {
    // given
    final Member member = new Member();
    member.setUsername("test_member");
    member.setPassword("test123");

    when(memberRepository.findByUsername(any(String.class))).thenReturn(member);

    // when
    UserDetails user = service.loadUserByUsername(member.getUsername());

    // then
    assertThat(user.getUsername(), is(member.getUsername()));
    assertThat(user.getPassword(), is(member.getPassword()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadUserByUserNameFailBecauseUsernameIsEmpty() throws Exception {
    // given
    final String username = "";

    // when
    service.loadUserByUsername(username);

    //then
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadUserByUserNameFailBecauseUsernameIsNull() throws Exception {
    // given
    final String username = null;

    // when
    service.loadUserByUsername(username);

    //then
  }

  @Test(expected = UsernameNotFoundException.class)
  public void testLoadUserByUserNameFailBecauseUsernameIsNotExist() throws Exception {
    // given
    final String username = "test_member";

    when(memberRepository.findByUsername(any(String.class))).thenReturn(null);

    // when
    service.loadUserByUsername(username);

    // then
  }
}
