package com.protostellar.zugplaner.trackandpredict.domain;

import com.protostellar.zugplaner.trackandpredict.model.checklist.blocks.yesno.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class YesNoBlockTests {

  @Test
  void testStatus() {
    YesNoBlock yesNoBlock = YesNoBlock
      .empty(Name.from("cooling_water"))
      .withStatus("yes");
    Map<String, Object> expected = new HashMap<>();
    expected.put("cooling_water", "yes");
    Assertions.assertThat(expected).isEqualTo(yesNoBlock.getContent());
    Assertions.assertThat(yesNoBlock.getName().getValue()).isEqualTo("cooling_water");
  }

  @Test
  void testStatusWithComment() {
    YesNoBlock yesNoBlock = YesNoBlock.empty(Name.from("motorolstand"))
      .withStatus("no")
      .withComment("hello");
    Map<String, Object> expected = new HashMap<>();
    expected.put("motorolstand", "no");
    expected.put("comment_motorolstand", "hello");
    Assertions.assertThat(expected).isEqualTo(yesNoBlock.getContent());
    Assertions.assertThat(yesNoBlock.getName().getValue()).isEqualTo("motorolstand");
  }

  @Test
  void testYesStatusWithCommentShouldNotTakeCommentIntoAccount() {
    YesNoBlock yesNoBlock = YesNoBlock.empty(Name.from("motorolstand"))
      .withStatus("yes")
      .withComment("hello");
    Map<String, Object> expected = new HashMap<>();
    expected.put("motorolstand", "yes");
    Assertions.assertThat(expected).isEqualTo(yesNoBlock.getContent());
    Assertions.assertThat(yesNoBlock.getName().getValue()).isEqualTo("motorolstand");
  }

  @Test
  void testStatusWithCommentAndLiter() {
    YesNoAmountBlock yesNoBlock = YesNoBlock.empty(Name.from("motorolstand"))
      .withStatus("no")
      .withComment("hello")
      .toYesNoAmount("10");
    Map<String, Object> expected = new HashMap<>();
    expected.put("motorolstand", "no");
    expected.put("comment_motorolstand", "hello");
    expected.put("liter_motorolstand", "10");
    Assertions.assertThat(expected).isEqualTo(yesNoBlock.getContent());
  }
}
